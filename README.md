MVC_Test_app

GET /api/v1/dashboard/ - отдает html ссылками ниже для удобства разработки

POST /api/v1/site/login - json логин вида
{
  "email": "111",
  "password": "111"
}

POST /api/v1/site/register - json регистрация вида 

{
  "email": "333",
  "password": "333",
  "firstName": "333",
  "lastName": "333"
}

GET /api/v1/admin/oauth - логин через google аккаунт, для администраторов указанных в config.json

POST /logout

GET /api/v1/dashboard/me -  отдает данные пользователя

GET /api/v1/dashboard/checks - отдает все чеки этого пользователя

GET /api/v1/dashboard/checks/:id - отдает конкретный чек включая продукты этого чека

POST /api/v1/dashboard/checks - принимает json чека с продуктами

GET /api/v1/dashboard/products - отдает все продукты этого пользователя

GET /api/v1/dashboard/products/:id - отдает конкретный продукт

GET /api/v1/admin/users -  все пользователи

GET /api/v1/admin/users/:id -  конкретный пользователь


фильтры

?limit
?offset
?afterDate
?beforeDate

сортировка

?createdAt
?alphabet

Date format "yyyy-MM-dd'T'HH:mm:ss"
without timezone
