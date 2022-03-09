<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url value="/styles/form.css" var="formStyle"/>
<!DOCTYPE html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${formStyle}"/>
</head>
<body>

<form class="audio" method="post" onsubmit="addAudio(event)" enctype="multipart/form-data">
    <script>
        const addAudio = (event) => {
            event.preventDefault();
            const actual = Object.fromEntries(new FormData(event.target).entries());
            delete actual.track;
            const formData = new FormData();
            formData.append("audio", new Blob(
                [JSON.stringify(actual)],
                {type: "application/json"}
            ));
            formData.append("track", document.querySelector("#track").files[0]);
            fetch(
                "${pageContext.request.contextPath}/audio/add",
                {
                    method: "post",
                    body: formData
                }
            ).then(response => response.json())
                .then(console.log);
        };
    </script>
    <label for="id">Audio ID</label>
    <input type="text" name="id" id="id"/>
    <label for="title">Title</label>
    <input type="text" name="title" id="title"/>
    <label for="artist">Artist</label>
    <input type="text" name="artist" id="artist"/>
    <label for="album">Album</label>
    <input type="text" name="album" id="album"/>
    <label for="duration">Duration</label>
    <input type="text" name="duration" id="duration"/>
    <label for="track">Upload</label>
    <input style="display:none" type="file" name="track" id="track" accept="audio/mpeg" />
    <button type="submit" class="audio_submit">Submit</button>
</form>

</body>