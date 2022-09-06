/*
 * Reimbursment API DML
 * */

/*STATUSES*/
insert  into ers_reimbursment_statuses (status_id, status) values ('P', 'PENDING');
insert  into ers_reimbursment_statuses (status_id, status) values ('A', 'APPROVED');
insert  into ers_reimbursment_statuses (status_id, status) values ('D', 'DENIED');

/*TYPES*/
insert into ers_reimbursment_types (type_id, type) values ('F', 'FOOD');
insert into ers_reimbursment_types (type_id, type) values ('L', 'LODGING');
insert into ers_reimbursment_types (type_id, type) values ('O', 'OTHER');
insert into ers_reimbursment_types (type_id, type) values ('T', 'TRAVEL');

/*ROLES*/
insert into ers_user_roles (role_id, role) values ('E', 'Employee');
insert into ers_user_roles (role_id, role) values ('M', 'Manager');
insert into ers_user_roles (role_id, role) values ('A', 'Admin');

/* Dummy users*/
insert  into ers_users (user_id, username, email, password, given_name, surname, is_active, role_id) 
	values ('390e0787-0fb7-44f6-a0ff-e63719f6fa37', 'MaryJk', 'MJLive@company.org', 'password', 'Mary', 'Jenkins', true, 'E');


/*EXTRA UUIDS
 * 184ad055-6162-416c-8600-fd5977191cec
 * 8779c923-a9a9-4e95-a63a-2cd16ec37e5b
 * 
 * */

INSERT INTO ers_reimbursements (reimb_id, amount, submitted, resolved, description, receipt, payment_id, author_id, resolver_id, status_id, type_id) 
	VALUES ( '390e0787-0fb7-44f6-a0ff-e63719f6fa37', '56.69', '2022-08-31T11:57:37.985', null, 'This is a postgres test', 'Reciept', 'pAymentId', '390e0787-0fb7-44f6-a0ff-e63719f6fa37', null, 'P', 'F');
