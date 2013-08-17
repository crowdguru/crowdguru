function goTo(path){
	console.debug("activity=goTo;path=" + path);
	window.location = contextPath + path;
}

function formPostInProgress(evt) {
	if (evt.lengthComputable) {
	var percentComplete = Math.round(evt.loaded * 100 / evt.total);
	console.debug("activity=formPost;status=progress;percentage=" + percentComplete.toString());
	}
	else {
	console.debug("activity=formPost;status=progress;percentage=");
	}
}
	 
function formPostFailed(evt) {
	/* This event is raised when the server send back a response */
	console.error("activity=formPost;status=failed;");
}

function formPostAborted(evt) {
	console.info("activity=formPost;status=aborted;");
} 

function formPosted(){
	console.info("activity=formPost;status=completed;");
	$("#divSubmitSuccessNotification").removeClass('hidden');
}

function postForm(event, onloadHandler){
	event.preventDefault();
	var fd = new FormData($("#registration").get(0));
	var xhr = new XMLHttpRequest();
	console.debug("activity=postForm");
	xhr.upload.addEventListener("progress", formPostInProgress, false);
	xhr.addEventListener("load", formPosted, false);
	xhr.addEventListener("error", formPostFailed, false);
	xhr.addEventListener("abort", formPostAborted, false);
	xhr.open("POST", contextPath + "register");
	xhr.send(fd);
}

$(function(){
	$("#inputSubmitRegistrationForm").click(function(event){
		postForm(event);
	});
});