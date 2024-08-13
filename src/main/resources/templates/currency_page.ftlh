<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Сurrency quotes</title>

    <link rel="stylesheet" href="../css/currency_quotes.css">
</head>
<body>

<h2 class="h1-quotes">Currency quotes (${.now?string("dd-MM-yyyy")})</h2>

<table class="currency-table">
    <thead>
    <tr>
        <th>ID</th>
        <th>Code</th>
        <th>Name</th>
        <th>Value</th>
    </tr>
    </thead>
    <tbody>
    <#list currency_list as currency>
        <tr>
            <td>${currency.id}</td>
            <td>${currency.charCode}</td>
            <td>${currency.name}</td>
            <td>${currency.vunitRate}₽</td>
        </tr>
    </#list>
    </tbody>
</table>

</body>
</html>