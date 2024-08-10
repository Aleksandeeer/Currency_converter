<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Currency converter</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
<body>
    <table>
        <#list currency_list as currency>
            <tr>
                <td>
                    ${currency.id}
                </td>
                <td>
                    ${currency.charCode}
                </td>
                <td>
                    ${currency.nominal}
                </td>
                <td>
                    ${currency.name}
                </td>
                <td>
                    ${currency.value}
                </td>
            </tr>
        </#list>
    </table>
</body>
</html>