# batch-insert
## Introduction
Multi thread and database connection pool are used to generate SQL files and import them into database
## Component
+ druid
+ thread pool of java
+ tool class of mybatis
## Use
1. git clone https://github.com/wmmxsd/batch-insert.git
2. cd batch-insert && mvn package 
3. **java -jar BATCH-INSERT-1.0-SNAPSHOT.jar** or **java -jar BATCH-INSERT-1.0-SNAPSHOT.jar 1000 2000** _(1000:number of SQL lines in each file; 2000:number of files)_

