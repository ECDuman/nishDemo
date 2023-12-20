# nishDemo
AUTH:

POST http://localhost:8090/nishapi/auth/register  enter username and password to create a user

POST http://localhost:8090/nishapi/auth/login  enter your registered username and password to get JWT token


CUSTOMER:  DON'T FORGET TO GET JWT TOKEN FROM AUTH

POST http://localhost:8090/nishapi/addCustomer add new customers with documents

GET http://localhost:8090/nishapi/customers get all customers

GET http://localhost:8090/nishapi/customers/{id} get a customer by id

PUT http://localhost:8090/nishapi/update update a customer

DELETE http://localhost:8090/nishapi/deleteCustomer/{id} delete customer by id


DOCUMENT:  DON'T FORGET TO GET JWT TOKEN FROM AUTH

POST http://localhost:8090/nishapi/customers/{id}/uploadFile upload a new file for a customer

GET http://localhost:8090/nishapi/downloadDocument/{id} download a document by id

GET http://localhost:8090/nishapi/documents get all files

GET http://localhost:8090/nishapi/documents/{id} get a file by id

PUT http://localhost:8090/nishapi/updateDocument/{id} update document by id

DELETE http://localhost:8090/nishapi/deleteFile/{id} delete file by id
