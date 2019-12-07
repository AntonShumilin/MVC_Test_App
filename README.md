MVC_Test_app

GET /api/v1/dashboard/ - отдает html ссылками ниже для удобства разработки

GET /api/v1/dashboard/me -  отдает текущий аккаунт с текущим юзером

GET /api/v1/dashboard/checks - отдает все чеки этого аккаунта

GET /api/v1/dashboard/checks/:id - отдает конкретный чек включая продукты этого чека

POST /api/v1/dashboard/checks - принимает json чека с продуктами

GET /api/v1/dashboard/products - отдает все продукты этого аккаунта

GET /api/v1/dashboard/products/:id - отдает конкретный продукт

GET /api/v1/admin/users -  все юзеры

GET /api/v1/admin/users/:id -  конкретный юзер


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
