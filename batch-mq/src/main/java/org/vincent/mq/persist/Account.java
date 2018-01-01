package org.vincent.mq.persist;

/**
 * @Package: org.vincent.mq.persist <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/1/2 1:10 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2017 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/1/2. <br/>
 */

public class Account {
		private  String id;
		private  String name;
		private  String email;
		private  String password;
		private  String activated;

		public String getId() {
				return id;
		}

		public void setId(String id) {
				this.id = id;
		}

		public String getName() {
				return name;
		}

		public void setName(String name) {
				this.name = name;
		}

		public String getEmail() {
				return email;
		}

		public void setEmail(String email) {
				this.email = email;
		}

		public String getPassword() {
				return password;
		}

		public void setPassword(String password) {
				this.password = password;
		}

		public String getActivated() {
				return activated;
		}

		public void setActivated(String activated) {
				this.activated = activated;
		}
}
