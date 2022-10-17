# cubedata

Project to read json file and store data into NoSQl Database.

I have used mongodb here.

Prerequisites install mongodb at local.

Steps to install mongodb :-

Download mongodb from the https://fastdl.mongodb.org/linux/mongodb-linux-x86_64-ubuntu1604-3.6.23.tgz

```
wget https://fastdl.mongodb.org/linux/mongodb-linux-x86_64-ubuntu1604-3.6.23.tgz
```

Download the project at local using 

```
git clone https://github.com/mindcrusher11/cubedata.git
```

I have used Scala here as currently I am using java and scala more, I also added apis with python as well.

***Project Structure imn the app folder***
```

controllers - for the apis.
 
model - model class objects are defined here.

repository - database repositories are defined here.

service - business logic is defined here.

tservice - it contains interface for implementation of services.

utils - it contains code for utilities

views - it contains default views 

```
 
***Database Setup***

```
Unzip tar file downloaded for ubuntu

tar -xzf mongodb-linux-x86_64-ubuntu1604-3.6.23.tgz

cd mongodb-linux-x86_64-ubuntu1604-3.6.23/bin

./mongod

Server Started

Access the client using client 

./mongo

Database is up and running

Create Text Index in mongodb 

```

***Run Program***

```
cd cubedata
sbt test
```

It will add data to mongodb in first test case with json file stored in resources folder

Second test case will read all data from database

Third test case will search data based on text from two fields Content and Title

Text Index need to added to database for search functionaility to work.

Run First Test case using 

```
sbt "testOnly *LegislationControllerSpec -- -z import"
```

Now add text index using 

```
db.legislation.createIndex({"Content":"text", "Title":"text"]})
```

Now Data will be available for search 

Now all test cases can be test 

using

```
sbt test
```



