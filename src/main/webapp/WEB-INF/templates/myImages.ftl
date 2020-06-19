<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<#if images??>
    <#list images as document>
        <a href="http://localhost:8080/files/${document.pathForDownload}"><img width="400"
                                                                               src="/Users/catch_you/Downloads/JAVA_ENTERPRISE_3-master 4/Projects/Download_file_with_mail_sender/target/classes/files/${document.pathForDownload}"
                                                                               height="400"></a>
        <td>
            <a href="http://localhost:8080/files/${document.pathForDownload}">скачать</a>
        </td>
    </#list>
</#if>

</body>
</html>