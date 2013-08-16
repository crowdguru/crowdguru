function previewImage(file, image) {
	console.debug("activity=previewImage;name=" + file.name);
	var reader = new FileReader();
	reader.onload = function(e) {
		image.src = e.target.result;
	};
	reader.readAsDataURL(file);
}

function updatePhotoPreview() {
	if (this.files && this.files[0]) {
		var file = this.files[0];
		console.info("activity=selectPhoto;status=selected;name="
				+ file.name + ";size=" + file.size + ";type=" + file.type);
		previewImage(file, $("#imagePhoto").get(0));
	} else {
		console.info("activity=selectPhoto;status=deselected");
	}
}

function uploadProgress(evt) {
	if (evt.lengthComputable) {
		var percentComplete = Math.round(evt.loaded * 100 / evt.total);
		console.debug("activity=upload;status=progress;percentage="
				+ percentComplete.toString());
	} else {
		console.debug("activity=fileUpload;status=progress;percentage=");
	}
}

function uploadComplete(evt) {
	/* This event is raised when the server send back a response */
	console.info("activity=upload;status=complete;response="
			+ evt.target.responseText);
	window.location = contextPath + "tasks";
}

function uploadFailed(evt) {
	/* This event is raised when the server send back a response */
	console.error("activity=upload;status=failed;");
}

function uploadCanceled(evt) {
	console.info("activity=upload;status=cancelled;");
} 

function postForm(publish){
	var fd = new FormData($("#task").get(0));
	var xhr = new XMLHttpRequest();
	console.debug("activity=postForm");
	xhr.upload.addEventListener("progress", uploadProgress, false);
	xhr.addEventListener("load", uploadComplete, false);
	xhr.addEventListener("error", uploadFailed, false);
	xhr.addEventListener("abort", uploadCanceled, false);
	xhr.open("POST", contextPath + "tasks");
	xhr.send(fd);
}

function saveTask(event){
	console.info("activity=saveForm");
	postForm();
	return true;
}

function publishTask(event){
	console.info("activity=publishForm");
	$("#inputPublish").val("true");
	saveTask(event);
}

function submitButtonPreHandler(event){
	event.preventDefault();
	return true;
}

$(function() {
	$("#filePhoto").change(updatePhotoPreview);
	$("#buttonSave").click(submitButtonPreHandler);
	$("#buttonSave").click(saveTask);
	$("#buttonPublish").click(submitButtonPreHandler);
	$("#buttonPublish").click(publishTask);
	console.debug("activity=pageLoaded");
});