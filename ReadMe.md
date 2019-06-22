### curl samples 

#### get All Users
curl -s http://localhost:8080/voting/api/admin/users --user admin@gmail.com:admin

#### get User 6
curl -s http://localhost:8080/voting/api/admin/users/6 --user admin@gmail.com:admin

#### get All restaurants
curl -s http://localhost:8080/voting/api/user/restaurants --user user1@yandex.ru:password

#### get All restaurants by date
curl -s http://localhost:8080/voting/api/admin/restaurants/byDate?day=2019-03-04 --user user1@yandex.ru:password

#### vote
curl -s -X POST http://localhost:8080/voting/api/user/restaurants/vote?restaurantId=21 --user user1@yandex.ru:password

### Admin Restaurant Controller can:
#### get information about restaurants menus on date
get /api/admin/restaurants/{id}?day={day}
#### adding restaurant menu. In this case each dish will be added to dayMenu and to dictionary menu for restaurant
post /api/admin/restaurants/ with RequestBody RestaurantTo
#### updating restaurant menu. In this case if dish id is null, it will be added to dictionary. So we can choose existing meal from dictionary or add new one which will be added to dictionary(look for example in tests)
put /api/admin/restaurants/ with RequestBody RestaurantTo
#### deleting restaurant day menu
delete /api/admin/restaurants/{id}?day={day}
#### for combobox for choosing meal from menu dictionary of restaurant
get /api/admin/restaurants/{id}/menu
#### get list of all restaurants without menus for combobox 
get /api/admin/restaurants/list
