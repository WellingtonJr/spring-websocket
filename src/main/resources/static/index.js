// import SockJS from 'sockjs-client';
// import Stomp from 'stompjs';

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        // $("#conversation").show();
        console.log("Connected");
    }
    else {
        // $("#conversation").hide();
        console.log("Disconnected");
    }
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({ 'name': $("#name").val() }));
}

function showGreetings(message) {
    // console.log(message);
    console.log(message);
}

function connect() {
    var socket = new SockJS('http://192.168.10.16:8080/stomp-endpoint');
    console.log(socket);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreetings(JSON.parse(greeting.body));
        })
    })
}


$(function () {
    $("form").submit(function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });
})