CREATE TABLE user (
                      id VARCHAR(150) primary key not null ,
                      username VARCHAR(150) NOT NULL,
                      password VARCHAR(150) NOT NULL,
                      fullname VARCHAR(150) NOT NULL,
                      roleid  VARCHAR(150) NOT NULL,
                      createdby VARCHAR(150) NULL,
                      createddate TIMESTAMP NULL,
                      modifiedby  VARCHAR(150) NULL,
                      modifieddate TIMESTAMP NULL
);

CREATE TABLE role (
                      id VARCHAR(150) NOT NULL PRIMARY KEY ,
                      name VARCHAR(150) NOT NULL,
                      code VARCHAR(150) NOT NULL,
                      createdby VARCHAR(150) NULL,
                      createddate TIMESTAMP NULL,
                      modifiedby  VARCHAR(150) NULL,
                      modifieddate TIMESTAMP NULL
);

CREATE TABLE site (
                      id VARCHAR(150) NOT NULL PRIMARY KEY ,
                      name VARCHAR(150) NOT NULL,
                      code VARCHAR(150) NOT NULL,
                      createdby VARCHAR(150) NULL,
                      createddate TIMESTAMP NULL,
                      modifiedby  VARCHAR(150) NULL,
                      modifieddate TIMESTAMP NULL
);

CREATE TABLE device(
                       id VARCHAR(150) NOT NULL PRIMARY KEY ,
                       information VARCHAR(150) NOT NULL,
                       history VARCHAR(150) NOT NULL ,
                       siteid VARCHAR(150) NOT NULL,
                       createdby VARCHAR(150) NULL,
                       createddate TIMESTAMP NULL,
                       modifiedby  VARCHAR(150) NULL,
                       modifieddate TIMESTAMP NULL
);
CREATE TABLE cloneDevice(
                            STT bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
                            id VARCHAR(150) ,
                            information VARCHAR(150) NULL,
                            history VARCHAR(150) NULL ,
                            siteid VARCHAR(150) NULL,
                            createdby VARCHAR(150) NULL,
                            createddate TIMESTAMP NULL,
                            modifiedby  VARCHAR(150) NULL,
                            modifieddate TIMESTAMP NULL
);
ALTER TABLE device ADD CONSTRAINT fk_decive_site FOREIGN KEY (siteid) REFERENCES site(id);
ALTER TABLE user ADD CONSTRAINT fk_user_role FOREIGN KEY (roleid) REFERENCES role(id);
ALTER TABLE device ADD CONSTRAINT fk_device_user FOREIGN KEY (userid) REFERENCES user(id);