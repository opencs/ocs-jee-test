/*
 * BSD 3-Clause License
 * 
 * Copyright (c) 2019, Open Communications Security
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * 
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 * 
 * * Neither the name of the copyright holder nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package br.com.opencs.hr.jee.web.rest;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.opencs.hr.jee.core.dto.UserDTO;
import br.com.opencs.hr.jee.core.services.interfaces.UserService;
import br.com.opencs.hr.jee.web.rest.data.UserEntry;
import br.com.opencs.hr.jee.web.rest.data.UserListEntry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * Implementation of the REST UserResource. It is mapped in
 * "/rest/user/*".
 * 
 * @author Fabio Jun Takada Chino <fjtc@users.noreply.github.com>
 * @version 2019.03.06
 */
@Path("/rest/user")
public class UserResource {

	@EJB
	private UserService userService;

	/**
	 * Returns a list of all users.
	 * 
	 * @return The response.
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Operation(
			summary = "List all users in the database.", 
			tags = {"user" }, 
			description = "Return a list of all users i the database.", 
			responses = {
					@ApiResponse(
							description = "The user list.", 
							content = @Content(schema = @Schema(implementation = UserListEntry.class)))})
	public Response getList() {
		ArrayList<UserEntry> list = new ArrayList<>();
		
		for (UserDTO user: userService.listUsers()) {
			UserEntry entry;
			entry = new UserEntry();
			entry.setUserId(user.getUserId());
			entry.setEmail(user.getEmail());
			entry.setName(user.getName());
			list.add(entry);
		}
		UserListEntry userList = new UserListEntry();
		userList.setUsers(list);
		return Response.ok(userList).build();		
	}
	
	/**
	 * Returns all information of a user identified
	 * by its userID.
	 * 
	 * @param userId The userID to be used.
	 * @return The response.
	 */ 
	@GET
	@Path("{userId}") 
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Operation(
		summary = "List all users in the database.", 
		tags = {"user" }, 
		description = "Return a list of all users i the database.", 
		responses = {
				@ApiResponse(
						description = "The user list.", 
						content = @Content(schema = @Schema(implementation = UserListEntry.class))),
				@ApiResponse(
						responseCode = "404", 
						description = "User not found.")})
	public Response getUser(
			@Parameter(
					description="The userID.")
			@PathParam("userId") long userId) {
		UserDTO user;
		
		user = userService.findUserByID(userId);
		if (user != null) {
			UserEntry entry;
			entry = new UserEntry();
			entry.setUserId(user.getUserId());
			entry.setEmail(user.getEmail());
			entry.setName(user.getName());			
			entry.setCreationDate(user.getCreationDate());
			entry.setUpdateDate(user.getUpdateDate());
			return Response.ok(entry).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}	
}
