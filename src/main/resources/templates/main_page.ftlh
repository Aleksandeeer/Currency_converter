<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Currency converter</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="../css/main_page_style.css">
</head>
<body>
    <h1 style="display: flex; justify-content: center;">
        <#list currency_list as currency>
            <#if currency.charCode == "USD" || currency.charCode == "EUR">
                ${currency.charCode}: ${currency.value}
            </#if>
        </#list>
    </h1>

    <hr>

    <form action="/converting" method="post">
        <select name="first_list" class="classic">
            <#list currency_list as currency>
                <option value="${currency.charCode}" <#if currency.charCode == "USD">selected</#if>>
                    ${currency.name}
                </option>
            </#list>
        </select>

        <input name="input_currency_amount" class="text-box" type="number" step="0.01" min="0"
            <#if input_value?? && input_value != -1>
                value="${input_value}"
            </#if>
        >

        <select name="second_list" class="classic">
            <#list currency_list as currency>
                <option value="${currency.charCode}" <#if currency.charCode == "EUR">selected</#if>>
                    ${currency.name}
                </option>
            </#list>
        </select>

        <input class="text-box" type="text" step="0.01" min="0"
                <#if result_input != -1>
                    value="${result_input?string}"
                </#if>
        >


        <button type="submit">
            <span class="shadow"></span>
            <span class="edge"></span>
            <span class="front text">Конвертировать</span>
        </button>
    </form>

</body>
</html>