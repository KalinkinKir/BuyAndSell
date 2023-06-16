<!DOCTYPE html>
<html>
<head>
    <title>BuyAndSell</title>
</head>
<body>
<h1>BuyAndSell</h1><hr>
<h3>Подробная информация о пользователе: ${user.name}</h3>
<b>Номер телефона: </b> ${user.phoneNumber}<br>
<b>E-mail: </b> ${user.email}<br>
<h4>Товары пользователя ${user.name}</h4>
<#list products as product>
    <div>
        <p><b>${product.title}</b> ${product.price} руб. | <a href ="/products/${product.id}">Подробнее...</a></p>
    </div>
    <#else>
    <h3>Товаров у пользователя нет</h3>
</#list>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>
</body>
</html>