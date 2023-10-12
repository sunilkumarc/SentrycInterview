# SentrycInterview

Sample API endpoints:

### Name ascending filter
http://localhost:8080/api/v1/sellers/filter?page=1&size=5&sortDirection=NAME_ASC
<img src = "name_asc_filter.png">

### Name descending filter
http://localhost:8080/api/v1/sellers/filter?page=1&size=5&sortDirection=NAME_DESC
<img src = "name_desc_filter.png">

### Seller name filter
http://localhost:8080/api/v1/sellers/filter?sellerName=seller%209&page=1&size=5&sortDirection=NAME_DESC
<img src = "seller_name_filter.png">

### Producer ids filter
http://localhost:8080/api/v1/sellers/filter?sellerName=seller%209&page=1&size=5&sortDirection=NAME_DESC&producerIds=Producer%201
<img src = "producer_ids_filter.png">