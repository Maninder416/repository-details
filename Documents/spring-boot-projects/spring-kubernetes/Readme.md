```bash

1. We are creating spring boot app with java 17
2. In Controller, we pass the request param as page to get the list in desc order.
Means, the page added into the last will display first in the list.

For instance:

    {
        "id": 5,
        "title": "Maninder5",
        "url": "dataguise5.com",
        "createdAt": "2023-03-31T16:10:40.496042Z"
    },
    {
        "id": 4,
        "title": "Maninder4",
        "url": "dataguise4.com",
        "createdAt": "2023-03-31T16:10:40.495632Z"
    }

id 5 will display first.

In the service layer, we have defined the property for pagination:
Pageable pageable = PageRequest.of(pageNo,5, Sort.Direction.DESC,"createdAt");

it means that only 5 records will display on one page. Suppose, we have 10 records and we set the page
size to 5. So, first page we will see only 5 records of latest. If you want to see another records
just pass the page no as 2.

http://localhost:8080/api/bookmarks?page=2

it will display the data present on 2 page.

So, in addition to the data display on pages, we need more information like:
how many pages are avaialable.
how many total elements.
current page
does it has next page or not: return it boolean
does it has previous page or not: return it boolean.
is it firt page: return it true or false.
is it last page: boolean return

3. To achieve this, we created one dto class called "BookMarkDto". 
4. And change the return type for method getBookmarks in service layer with BookMarkDto.

Now output will be like this:

    {
            "id": 2,
            "title": "Maninder2",
            "url": "dataguise2.com",
            "createdAt": "2023-03-31T17:47:01.532387Z"
    },
    {
            "id": 1,
            "title": "Maninder1",
            "url": "dataguise1.com",
            "createdAt": "2023-03-31T17:47:01.510997Z"
    }
    ],
    "totalElements": 10,
    "totalPages": 2,
    "currentPage": 2,  
    "hasNext": false, It does not have next page because total pages are 2.
    "hasPrevious": true, //It has previous page called 1.
    "isFirst": false,  //Is it first page. Ans is no. 
    "isLast": true   //Is this last page. Ans is yes.
}

5. Next step, we will create DTO class, to display the data because meantime we are just displaying
the entity data and that is not a right approach. Sometimes, we don't want to display
all the data. We just need to display only the important data. In that we use dto
concept.

After creating the Mapper, we just pass it in the service layer:
Converting all the parameters to dto using map

Page<BookmarkDto> bookmarkDtoPage= bookmarkRepository.findAll(pageable).map(bookmarkMapper::toDto);

Still here, we are loading all the data first and then mapping it.
For instance: bookmarkRepository.findAll(pageable), we are doing this in above query.
That is not a right approach, because it loads all the data from database first and then
we map it. Instead of this, we can write new method in repository layer with custom data
that will load only required data.

we can do this way:

@Query("select new io.reactivestax.kubernetes.dto.BookmarkDto(b.id, b.title, b.url, b.createdAt) from Bookmark b")
    Page<BookmarkDto> findBookmarks(Pageable pageable);
    

benefit of using dto class over entity:
1. The main benefit of doing so is to avoid exposing sensitive or unnecessary
 information to the outside world.
2. Entity classes typically represent the persistence layer and are used
 to map objects to database tables. They often contain sensitive information, 
such as primary keys, foreign keys, and other fields that should not be
 exposed to the outside world

3. By creating a separate DTO class, you can control exactly what information
 is exposed to the outside world. This can be particularly important when designing
REST APIs,as you want to ensure that only the necessary information is being exposed to clients

4. Another benefit of using DTOs is that they allow you to decouple the internal representation
 of your data from the external representation. This means that you can change the internal
representation of your data (e.g., by adding or removing fields) without affecting the external
representation (i.e., the DTO).


```