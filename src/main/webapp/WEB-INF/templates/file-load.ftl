<#ftl encoding='UTF-8'>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>
<body>

<script>
    function sendFile() {
        // данные для отправки
        let formData = new FormData();
        // забрал файл из input
        let files = ($('#file'))[0]['files'];
        // добавляю файл в formData
        [].forEach.call(files, function (file, i, files) {
            formData.append("file", file);
        });
        <#--formData.append("${_csrf.parameterName}", "${_csrf.token}");-->

        $.ajax({
            type: "POST",
            url: "/files",
            data: formData,
            processData: false,
            contentType: false
        })
            .done(function (response) {
                alert(response)
            })
            .fail(function () {
                alert('Error')
            });
    }
</script>
<div>
    <input type="file" id="file" name="file" placeholder="Имя файла..."/>
    <button onclick="sendFile()">
        Загрузить файл
    </button>
    <input type="hidden" id="file_hidden">
    <#--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->
    <div class="filename"></div>
</div>
</body>
</html>