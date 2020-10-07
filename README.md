# news-task

This is the solution for the task that can be found in the root directory with the name "newspaper-task.pdf"

## instructions

The REST server can be started as any other java application.
It can also be started via the Docker command:
```
docker container run --detach --publish 8080:8080 --name newspaper-task tsilva1/newspaper-task:1.0.0-SNAPSHOT
```
Main can be located in NewspaperTaskApplication class.
Test can be located in NewspaperTaskApplicationTests class.
Any requested mapping that wasn't prepared will receive an error response.

### default configuration
endpoint: localhost:8080

##
### REST service locations
##
-upload news data: @POST localhost:8080/news/post

example request body structured as .xml file:

```
"
<?xml version="1.0" encoding="utf-8"?>
 <epaperRequest>
   <deviceInfo name="Browser" id="test@comp">
     <screenInfo width="1280" height="752" dpi="160" />
     <osInfo name="Browser" version="1.0" />
     <appInfo>
       <newspaperName>New name</newspaperName>
       <version>1.0</version>
     </appInfo>
   </deviceInfo>
   <getPages editionDefId="11" publicationDate="2017-06-08"/>
 </epaperRequest>
 "
```

example request:
```
curl --location --request POST 'localhost:8080/news/post' \
--header 'Content-Type: application/xml' \
--data-raw '<?xml version="1.0" encoding="utf-8"?>
<epaperRequest>
  <deviceInfo name="Browser" id="test@comp">
    <screenInfo width="1280" height="752" dpi="160" />
    <osInfo name="Browser" version="1.0" />
    <appInfo>
      <newspaperName>New name</newspaperName>
      <version>1.0</version>
    </appInfo>
  </deviceInfo>
  <getPages editionDefId="11" publicationDate="2017-06-08"/>
</epaperRequest> '
```

-get news collection: @GET localhost:8080/news/get?sort={sort}&page={page}&filter={filter}

sort can be either "appName" or "updateTs" any other will default to "appName".

page should be any value over 0, in the opposite case it will default to 1 and in case there is no content in a specific page an error message will pre presented.

filter must follow the sort field type "appName" expects a String, "updateTs" expects a Long.

example request:

```
curl --location --request GET 'localhost:8080/news/get?sort=appName&page=1&filter=n'
```