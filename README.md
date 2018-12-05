# Android_Xkcd_Persistance

## Introduction

This app will add some persistence to the xkcd app to allow users to set certain comics as favorites and see their view history.

## Instructions

### Part 1 - Database Schema

First thing we need to do is design a schema for our database. What data do we want to store? We could store the entire comic. This has it's own advantages and disadvantages, it would improve load times, but also add a lot of local storage. For this project, we'll store a history of viewed comics, when they were viewed and allow the user to mark them as a favorite.

1. Our DB will be a single table which will hold 3 columns, an `integer` ID, an `integer` for the timestamp when the item was last read, and an `integer` which will serve as a bool replacement (1 for true and 0 for false)

### Part 2 - Database Contract

The contract is where we define our schema in Android.

1. Create a new Java class called `XkcdDbContract`. Inside of that class, create a static class called `ComicEntry` which will `implements` `BaseColumns`.

> BaseColumns gives us a default _ID field which we can use and is used when wrapping your database with other classes

1. Create a constant data member for your table's name and one for each of the column names.

> We don't need a data member for ID as it is provided with the BaseColumns

1. In db-fiddle, write a CREATE TABLE query to build your comics table as defined in Part 1
2. In your contract class, create a constant `String` called `SQL_CREATE_TABLE`. Copy the query from db-fiddle to this member
3. Replace all column names in your create member with references to the data members.
4. Create a constant data member called `SQL_DELETE_TABLE` this will delete the entire table

> Use the `DROP TABLE IF EXISTS` query to delete your table, just put your table name at the end

1. Be sure that your SQL queries all end in a semi colon.

### Part 3 - Database Helper

1. Create a new `XkcdDbHelper` class which `extends` `SQLiteOpenHelper`
2. Add data members for DATABASE_VERSION and DATABASE_NAME
3. Add a constructor to the class which accepts a `Context` and calls the super constructor passing in the context, db name, `null`, and db version.
4. Override the `onCreate` method. Call `execSQL` on the `SQLiteDatabase` object which is passed into the method. Pass the `SQL_CREATE_TABLE` data member from your contract class to `execSQL`
5. Override the `onUpgrade` method. Call `execSQL` on the passed database and pass in your `SQL_DELETE_TABLE` data member.

### Part 4 - SQL Dao

We will need to implement the CRUD(L) functions in a DAO class to facilitate database interactions and make them clearer to understand. After the CRUD functions are implemented using ids, we can add additional functions as necessary, like filtered read, broader updates, or joins as necessary.

1. First, we need a method to get a singleton instance of the database.

   > A singleton is an object which can only be instantiated once. We can do this by only having private constructors and creating a `getInstance` method which would normally return a reference to the object instance, however, in this case, it won't return anything and will just store the value in a static member so the name will be different

2. Create a data member of type `SQLiteDatabase` 

3. Write a method called `initializeInstance` which will create an instance of your `XkcdDbHelper` class, call `getWritableDatabase` on the object and then store that all in the `SQLiteDatabase` object. This is all done if the `SQLiteDatabase` object is `null`

   > In all methods which need to access the database member, call the `initializeInstance` method first.

4. We now need an object to store our db results in as it will store things that aren't in the web api objects. Create a class called `XkcdDbInfo`

5. In this class, add data members to match the ones in your db schema (the id already exists in the comic object so that isn't necessary)

6. Add getters and setters

7. In your `XkcdComic` object, add a data member of type `XkcdDbInfo`, as well as a getter and setter for it.

8. Write a method called `createComic` which accepts a `XkcdComic` object.

9. Create a `ContentValues` variable in this method and assign it using the empty constructor for that class.

10. call the `put` method on that variable for each column in your database

    > be sure to put the column name constant as the first parameter and the desired value from the comic object as the second parameter

11. Once the values object is built, call `insert` on the database object and pass the name of the table, `null`, and the values object

12. Write a method called `readComic`, this will accept an int id. It will return a `XkcdDbInfo` object.

13. In db-fiddle, write a query to read a single entry from the table when provided with the id

14. Take that query and copy it into a string value in your method.

15. Replace the hard coded ID with the one provided in the method signature

16. Call the `rawQuery` method on your database, pass it in your query string and a null value. Store the resulting cursor.

17. If a call to `moveToNext` on that cursor returns true retrieve all the values from that cursor and stores them in a `XkcdDbInfo` object

    > remember, there are two parts to retrieving each piece of data from a cursor, first you call `getInt` on it (or whatever type you want), then you must pass in the column index which is done by calling `getColumnIndexOrThrow` and passing in the column name

18. Return the constructed `XkcdDbInfo`  object

    > For added peace of mind call `getCount` on your cursor and make sure it returns 1 before getting the data

19. Write a method called `updateComic` which accepts a `XkcdDbInfo` object.

20. First we want to write a where clause and check it. Create two String variables, one for the where clause and one for the whole query.

    > Write the where clause then write the rest of the query and tack the where clause onto the end of it

21. Call `rawQuery` with the complete clause and store the result

22. Check that the result affects the correct number of entries (in this case, 1) by checking the `getCount` method

23. Build a `ContentValues` object like in the create method.

24. Pass it into the database's `update` method, then pass in the table name, your values object, the where clause, then null

25. Create a method called `deleteComic` which accepts an id

26. Test your where clause like in the update method

27. Write a DELETE query using your where clause.

28. Call `execSQL` with your query.

    > execSQL will execute an SQL query like rawSQL will, but will not return a result.

29. Test your methods by entering test data, reading it, updating it and then deleting it. There is no need to attach this to the GUI yet, just use the debugger or log for now.

If you get to this point, you can continue or wait until tomorrow when I have these instructions complete.

### Part 5 - Connect to Comic DAO



### Part 6 - Allow for Favorite Selection

### Part 7 - Build Favorites/History Page

#### Challenge

Improve the get random comic feature, by only retrieving unseen comics until they have all been viewed.
