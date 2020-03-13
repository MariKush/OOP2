let camera, renderer, scene, wSocket, connected, core;


init();
animate();

function connect() {
    wSocket = new WebSocket("ws://localhost:8080/action");
    wSocket.onmessage = onMessage;
    connected = true;
}

function onMessage(event) {
    const json = JSON.parse(event.data);
    let x, y, z;
    console.log(json.command);
    if (json.command === "new_position") {
        x = json.new_x;
        y = json.new_y;
        z = json.new_z;
        setCorePosition(x, y, z);
    } else if (json.command === "finish") {
        setForAll(false);
        newPosition();
    }
}

function setForAll(bool ) {
    document.getElementById("startX").disabled = bool;
    document.getElementById("startY").disabled = bool;
    document.getElementById("startZ").disabled = bool;
    document.getElementById("btn").disabled = bool;
    document.getElementById("startVX").disabled = bool;
    document.getElementById("startVY").disabled = bool;
    document.getElementById("startVZ").disabled = bool;
    document.getElementById("gravity").disabled = bool;
}

function start() {
    if (!connected || wSocket.closed) {
        connect();
    }
    setForAll(true);
    const obj = {
        command: "fire",
        "x": document.getElementById("startX").value,
        "y": document.getElementById("startY").value,
        "z": document.getElementById("startZ").value,
        "vx": document.getElementById("startVX").value,
        "vy": document.getElementById("startVY").value,
        "vz": document.getElementById("startVZ").value,
        "gravity": document.getElementById("gravity").value
    };
    const msg = JSON.stringify(obj);
    console.log(obj);
    sendMessage(msg);
}

window.addEventListener("keypress", manageKey, false);

function manageKey(e) {
    if (e.keyCode === 32) {
        start();
    }
}

function sendMessage(msg) {
    waitForSocketConnection(wSocket, function () {
        console.log("message sent!!!");
        wSocket.send(msg);
    });
}


function waitForSocketConnection(socket, callback) {
    setTimeout(
        function () {
            if (socket.readyState === 1) {
                console.log("Connection is made");
                if (callback != null) {
                    callback();
                }

            } else {
                console.log("wait for connection...");
                waitForSocketConnection(socket, callback);
            }
        }, 5); // wait 5 milisecond for the connection
}


function init() {
    const divForImage = document.getElementById("image");

    scene = new THREE.Scene();
    camera = new THREE.PerspectiveCamera(70, window.innerWidth / window.innerHeight, 1, 10000);
    camera.position.set(300, 300, 300);
    scene.add(camera);

    const light = new THREE.SpotLight(0xffffff, 1.5);
    light.position.set(300, 300, 300);
    scene.add(light);

    const helper = new THREE.GridHelper(3000, 300);
    helper.position.y = 0;
    helper.material.opacity = 0.25;
    helper.material.transparent = true;
    scene.add(helper);

    renderer = new THREE.WebGLRenderer({antialias: true});
    renderer.setClearColor(new THREE.Color('white'));
    renderer.setPixelRatio(window.devicePixelRatio);
    renderer.setSize(window.innerWidth, window.innerHeight - document.getElementById("info").offsetHeight);
    divForImage.appendChild(renderer.domElement);

    new THREE.OrbitControls(camera, renderer.domElement);
    new THREE.TransformControls(camera, renderer.domElement);

    //create sphere
    const sphereGeometry = new THREE.SphereGeometry(20, 50, 50, 0, Math.PI * 2, 0, Math.PI * 2);
    core = new THREE.Mesh(sphereGeometry, new THREE.MeshStandardMaterial({color: "#20296d"}));
    newPosition();
    scene.add(core);
    sphereGeometry.dynamic = true;
}

function setCorePosition(x, y, z) {
    core.position.x = x;
    core.position.y = y;
    core.position.z = z;
}

function animate() {
    requestAnimationFrame(animate);
    render();
}

function render() {
    renderer.render(scene, camera);
}

function newPosition() {
    core.position.x = document.getElementById("startX").value;
    core.position.y = document.getElementById("startY").value;
    core.position.z = document.getElementById("startZ").value;
}