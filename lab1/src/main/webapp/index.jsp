<!DOCTYPE html>
<html lang="html">
<link rel="stylesheet" href="style.css">
<body>

<div id="info">
    <div class="container-name">
        <div class="div1">startX</div>
        <div class="div2"><INPUT TYPE="NUMBER" Id="startX" onchange="newPosition()" MIN="-200" MAX="200" STEP="5"
                                 VALUE="0" SIZE="6"></div>
        <div class="div1">startY</div>
        <div class="div2"><INPUT TYPE="NUMBER" Id="startY" onchange="newPosition()" MIN="1" MAX="200" STEP="5"
                                 VALUE="70" SIZE="6"></div>
        <div class="div1">startZ</div>
        <div class="div2"><INPUT TYPE="NUMBER" Id="startZ" onchange="newPosition()" MIN="-200" MAX="200" STEP="5"
                                 VALUE="0" SIZE="6"></div>
        <div>
            <button type="button" iD="btn" onclick="start()">Start!</button>
        </div>
    </div>
    <div class="container-name">
        <div class="div1">startVX</div>
        <div class="div2"><INPUT TYPE="NUMBER" Id="startVX" MIN="-100" MAX="100" STEP="0.5" VALUE="20" SIZE="6"></div>
        <div class="div1">startVY</div>
        <div class="div2"><INPUT TYPE="NUMBER" Id="startVY" MIN="1" MAX="100" STEP="0.5" VALUE="20" SIZE="6"></div>
        <div class="div1">startVZ</div>
        <div class="div2"><INPUT TYPE="NUMBER" Id="startVZ" MIN="-100" MAX="100" STEP="0.5" VALUE="20" SIZE="6"></div>
        <div class="div1">gravity</div>
        <div class="div2"><INPUT TYPE="NUMBER" Id="gravity" MIN="-50" MAX="50" STEP="0.5" VALUE="10" SIZE="6"></div>
    </div>
</div>

<div id="image"></div>

<script src="three.js"></script>
<script src="controls/OrbitControls.js"></script>
<script src="controls/TransformControls.js"></script>
<script src="script.js"></script>
</body>
</html>
