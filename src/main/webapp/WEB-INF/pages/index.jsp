<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url value="/styles/index.css" var="indexStyle"/>
<!DOCTYPE html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${indexStyle}"/>
</head>
<body>
<script>
    const removeAudio = (id, callback) => {
        fetch(`${pageContext.request.contextPath}/audio/\${id}`, {method: "delete"})
            .then(response => response.json())
            .then(data => document.getElementById(`\${data.id}`).remove());
    }
</script>
<div class="audios">
    <c:forEach var="audio" items="${audios}">
        <div class="audio" id="${audio.id}">
            <div class="audio_title">${audio.title}</div>
            <div class="audio_artist">${audio.artist}</div>
            <div class="audio_album">${audio.album}</div>
            <div class="audio_controls">
                <a href='<c:url value="/audio/${audio.id}"/>'>Download</a>
                <svg width="10" height="10" onclick="removeAudio(${audio.id})">
                    <path d="M0.0 0.0 L10.0 10.0 M10.0 0.0 L0.0 10.0" stroke-width="1px" stroke="red"></path>
                </svg>
            </div>
        </div>
    </c:forEach>
</div>
<a href='<c:url value="/web/audio/info"/>' class="audio_add">Add Audio</a>
</body>
