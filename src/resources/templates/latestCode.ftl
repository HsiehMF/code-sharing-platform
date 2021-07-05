<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello World !</title>
</head>
<body>
    <#list codeArrayList as item>
        <div class="container">
            <p>${item.date}</p>
            <div class="code_section" style="background: #DEDEDE; width: 500px; padding: 10px;">
                ${item.code}
            </div>
        </div>
    </#list>
</body>
</html>