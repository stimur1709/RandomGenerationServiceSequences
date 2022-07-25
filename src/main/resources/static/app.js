var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#result").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/result', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendSize() {
    stompClient.send("/app/hello", {}, JSON.stringify({'size': $("#size").val()}));
}

function sendGenerate() {
    stompClient.send("/app/generate", {}, JSON.stringify({'size': $("#size").val()}));
}
function sendAuto() {
    setInterval(() => {
        stompClient.send("/app/generate", {}, JSON.stringify({'size': $("#size").val()}));
    }, 10000);
}


function showGreeting(message) {
    $("#result").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendSize();
    });
    $("#generate").click(function () {
        sendGenerate();
    });
    $("#auto").click(function () {
        sendAuto();
    });
});