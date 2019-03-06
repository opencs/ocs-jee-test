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
package br.com.opencs.hr.jee.web.jsf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * This class implements the listSystemPropertiesBean managed beam.
 * 
 * @author Fabio Jun Takada Chino <fjtc@users.noreply.github.com>
 * @version 2019.03.04
 */
@ManagedBean(name="listSystemPropertiesBean")
@ViewScoped
public class ListSystemPropertiesBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	/**
	 * Placeholder for the system property. 
	 */
	public static class SystemProperty implements Comparable<SystemProperty>{
		private String name;
		private String value;
		
		public SystemProperty(String name, String value) {
			super();
			this.name = name;
			this.value = value;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getValue() {
			return value;
		}
		
		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public int compareTo(SystemProperty o) {
			return this.getName().compareTo(o.getName());
		}
	}
	
	private List<SystemProperty> properties;
	
	/**
	 * Initializes the list.
	 */
	@PostConstruct
	public void postConstruct() {
		doRefresh();
	}
	
	/**
	 * Refreshes the system property list.
	 */
	public void doRefresh() {
		ArrayList<SystemProperty> props = new ArrayList<>();
		
		for (Entry<Object, Object> entry: System.getProperties().entrySet()) {
			props.add(new SystemProperty(entry.getKey().toString(), entry.getValue().toString()));
		}
		Collections.sort(props);
		this.properties = props;
	}

	public List<SystemProperty> getProperties() {
		return properties;
	}
}
