<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Latest</title>
</head>
<body>
    <#list codeArrayList as item>
        <div class="container">
            <span id="load_date">${item.date}</span>
            <div class="code_snippet" style="background: #DEDEDE; width: 500px; padding: 10px;">
                <pre id="code_snippet">${item.code}</pre>
            </div>
        </div>
    </#list>
</body>
</html>