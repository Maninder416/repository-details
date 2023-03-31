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







```