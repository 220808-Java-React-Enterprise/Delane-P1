/*
 * Reimbursment API DDL
 * */

drop table if exists ERS_REIMBURSEMENTS, ERS_USERS, ERS_USER_ROLES, ERS_REIMBURSMENT_TYPES, ERS_REIMBURSMENT_STATUSES ;


create table ERS_REIMBURSMENT_STATUSES (
	status_id varchar primary key,
	status varchar unique
);


create table ERS_REIMBURSMENT_TYPES (
	type_id varchar primary key,
	type varchar unique
);


create table ERS_USER_ROLES (
	role_id varchar primary key,
	role varchar unique
);


create table ERS_USERS (
	user_id varchar primary key,
	username varchar unique not null,
	email varchar unique not null,
	password varchar not null,
	given_name varchar not null,
	surname varchar not null,
	is_active boolean,
	role_id varchar,
	
	constraint fk_role_id
		foreign key (role_id) references ERS_USER_ROLES(role_id)
);



create table ERS_REIMBURSEMENTS (
	reimb_id varchar primary key,
	amount money not null,	/*(error)-->type number does not exist. Changed to money.*/
	submitted timestamp not null,
	resolved timestamp,
	description varchar not null,
	receipt bytea,			/**(error)-->type blob does not exist. Changed to bytea.*/
	payment_id varchar,
	
	author_id varchar not null,
	resolver_id varchar,
	status_id varchar not null,
	type_id varchar not null,
	
	constraint fk_author_id
		foreign key (author_id) references ERS_USERS(user_id),
	constraint fk_resolver_id
		foreign key (resolver_id) references ERS_USERS(user_id),
	constraint fk_status_id
		foreign key (status_id) references ERS_REIMBURSMENT_STATUSES(status_id),
	constraint fk_type_id
		foreign key (type_id) references ERS_REIMBURSMENT_TYPES(type_id)
	
);


