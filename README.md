# School API Rest

Management of Students, Subjects and Teachers.

Security: You are allowed to acces only to "/auth/singin(or)singup". After creating the account and get logged in, a JWT is provided to authorize the requests. 

Controller: GET, PUT, PATCH, POST, UPDATE and DELETE methods. *Uses javax.validation "@Valid" annotation for "RequestBody" to ensure correct input information according to the object. *Works with a "Data transfer object" to avoid the manipulation of the whole entity at this point.

Service & Mapper: Handle the logic of transfering DTO to entity and entity to DTO. Mappers handle the recursive issue beetwhin "@ManyToMany & @ManyToOne" relationships.

Entity: "@EntityListeners" is used to make the object able for fill in "@CreationDate and @LastModifiedDate" automatically. Also entities have a "deleted" boolean attribute for soft delete instead of hard delete of the database.

Dto: Here are defined attributes which demand validation to the controller.

Exception: Custom exception created with an exception handler to provide a full entity response with message, body, headers, status and web request).
