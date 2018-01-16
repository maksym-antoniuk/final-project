<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Welcome</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="styles/start.css">
    <link rel="stylesheet" href="styles/w31.css">
    <link rel="stylesheet" href="styles/font-awesome.css">
</head>
<body>

<!-- SHOWCASE -->
<section class="showcase">
    <div class="w3-container w3-center">
        <h1 class="w3-text-shadow w3-animate-opacity">Go Anywhere</h1>
        <hr>
        <p class="w3-animate-opacity">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo</p>
        <button class="w3-button w3-red w3-large w3-opacity" id="showcaseButton">Start Here</button>
    </div>
</section>

<!-- ABOUT -->
<section id="about" class="section">
    <div class="w3-container">
        <div class="w3-row-padding">
            <div class="w3-col m5">
                <img src="img/about.jpg">
            </div>
            <div class="w3-col m7">
                <button onclick="accFunction('driverQ')" class="w3-btn-block w3-left-align">
                    What We Do</button>

                <div id="driverQ" class="w3-container w3-show">
                    <h3>Требования к водителю</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                </div>

                <button onclick="accFunction('managerQ')" class="w3-btn-block w3-left-align">
                    What We Do</button>

                <div id="managerQ" class="w3-container w3-hide">
                    <h3>Требования к менеджеру</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- SERVICES HEADING -->
<section id="services" class="section w3-red w3-hover-opacity">
    <div class="w3-container w3-center">
        <h1 class="w3-text-shadow">Виды перевозок</h1>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
    </div>
</section>

<!-- SERVICES -->
<section class="section w3-light-grey">
    <div class="w3-container w3-center">
        <div class="w3-row">
            <div class="w3-col m3">
                <i class="fa fa-comment w3-red w3-padding-small w3-round-xlarge"></i>
                <h3>Internet Marketing</h3>
                <p>Lorem ipsum dolor sit amet</p>
            </div>
            <div class="w3-col m3">
                <i class="fa fa-search w3-red w3-padding-small w3-round-xlarge"></i>
                <h3>SEO</h3>
                <p>Lorem ipsum dolor sit amet</p>
            </div>
            <div class="w3-col m3">
                <i class="fa fa-cubes w3-red w3-padding-small w3-round-xlarge"></i>
                <h3>Software Development</h3>
                <p>Lorem ipsum dolor sit amet</p>
            </div>
            <div class="w3-col m3">
                <i class="fa fa-cloud w3-red w3-padding-small w3-round-xlarge"></i>
                <h3>Cloud Hosting</h3>
                <p>Lorem ipsum dolor sit amet</p>
            </div>
        </div>
    </div>
</section>

<!-- FOOTER -->
<%@include file="/views/footer.jspf" %>

<!-- MODAL -->
<div id="start-modal" class="w3-modal">
    <div class="w3-modal-content" id="modal-content">
        <header class="w3-container w3-red">
            <span class="w3-closebtn" id="closeModal">&times;</span>
            <h2>Get Started</h2>
        </header>
        <div class="w3-container" id="mytabs">
            <h2>Active Tabs</h2>
            <p>To highlight the current tab/page the user is on, add a color class, and use JavaScript to update the active link.</p>

            <div class="w3-bar w3-black">
                <button class="w3-bar-item w3-button tablink w3-red" onclick="openRole(event,'manager')">Manager</button>
                <button class="w3-bar-item w3-button tablink" onclick="openRole(event,'driver')">Driver</button>
            </div>

            <div id="manager" class="w3-container w3-border role">
                <h2>Manager</h2>
                <div class="w3-container">
                    <form method="post" action="registration">
                        <div class="w3-section">
                            <input type="hidden" name="role" value="manager">
                            <%@include file="/views/namemail.jspf" %>
                            <button class="w3-black w3-btn-block w3-section w3-padding">Submit</button>
                        </div>
                    </form>
                </div>
            </div>

            <div id="driver" class="w3-container w3-border role">
                <h2>Driver</h2>
                <div class="w3-container">
                    <form method="post" action="registration">
                        <div class="w3-section">
                            <input type="hidden" name="role" value="driver">
                            <%@include file="/views/namemail.jspf" %>
                            <label>Car Mark</label>
                            <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Enter Car Mark" id="carmark" name="carmark" required>
                            <label>Car Model</label>
                            <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Enter Car Model" id="carmodel" name="carmodel" required>
                            <label>Car Number</label>
                            <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Enter Car Number" id="carnumber" name="carnumber" required>
                            <label>Type Bodywork</label>
                            <select class="w3-select" name="type_bodywork" required>
                                <option value="" disabled selected>Choose your type bodywork</option>
                                <option value="tank">Tank</option>
                                <option value="bulk">Bulk</option>
                                <option value="animal">Animal</option>
                                <option value="container">Container</option>
                                <option value="car">Car</option>
                            </select>
                            <br>
                            <br>
                            <label>Carrying capacity</label>
                            <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Enter Carrying Capacity" id="capacity" name="capacity" required>
                            <label>Max Volume</label>
                            <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Enter Max Volume" id="volume" name="volume" required>
                            <button class="w3-black w3-btn-block w3-section w3-padding">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="scripts/main.js"></script>
</body>
</html>
