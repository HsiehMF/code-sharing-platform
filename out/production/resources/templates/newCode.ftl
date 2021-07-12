<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Create</title>
</head>

<body>
    <textarea id="code_snippet" style="width: 500px; height:150px;" placeholder="Write your code here"></textarea>
    <br>
    <span>Time restriction: </span><input id="time_restriction" value=0 type="text" />
    <br>
    <span>Views restriction: </span><input id="views_restriction" value=0 type="text" />
    <br>
    <button id="send_snippet" type="submit" onclick="send()">Submit</button>

    <script type="text/javascript">
        function send() {
            let object = {
                "code": document.getElementById("code_snippet").value,
                "time": document.getElementById("time_restriction").value,
                "views": document.getElementById("views_restriction").value
            }

            let json = JSON.stringify(object);
            console.log(json);

            let xhr = new XMLHttpRequest();
            xhr.open("POST", '/api/code/new', false);
            xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
            xhr.send(json);

            if (xhr.status === 200) {
                alert("Success!");
            }
        }
    </script>
</body>

</html>