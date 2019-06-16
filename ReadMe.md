### curl samples 

#### get All Users
curl -s http://localhost:8080/voting/api/admin/users --user admin@gmail.com:admin

#### get User 6
curl -s http://localhost:8080/voting/api/admin/users/6 --user admin@gmail.com:admin

#### get All restaurants
curl -s http://localhost:8080/voting/api/user/restaurants?day=2019-03-04 --user user1@yandex.ru:password

#### vote
curl -s -X POST http://localhost:8080/voting/api/user/restaurants/vote?restaurantId=21 --user user1@yandex.ru:password

#Admin Restaurant Controller can:
# get information about restaurants menus on date
get /api/admin/restaurants/{id}?day={day}
# adding restaurant menu
post /api/admin/restaurants/ with RequestBody RestaurantTo
# updating restaurant menu
put /api/admin/restaurants/ with RequestBody RestaurantTo
# deleting restaurant day menu
delete /api/admin/restaurants/{id}?day={day}
# for combobox for choosing meal from menu dictionary of restaurant
get /api/admin/restaurants/{id}/menu
# get list of all restaurants without menus for combobox 
get /api/admin/restaurants/list
