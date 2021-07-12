<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Code</title>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>
        hljs.initHighlightingOnLoad();
    </script>
</head>

<body>
    <span id="load_date">${codeSnippetHtml.date}</span>
    <#if codeSnippetHtml.secret == true>
        <#if codeSnippetHtml.views gt 0>
            <br>
            <span id="views_restriction">${codeSnippetHtml.views}</span> more views allowed
        </#if>
        <#if codeSnippetHtml.time gt 0>
            <br>
            The code will be available for <span id="time_restriction">${codeSnippetHtml.time}</span> seconds
        </#if>
    </#if>
    <pre id="code_snippet">
        <code>${codeSnippetHtml.code}</code>
    </pre>
</body>

</html>