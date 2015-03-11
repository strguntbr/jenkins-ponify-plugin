function replaceBall(balls) {
  var i = Math.floor((Math.random() * balls.length));
  var ball = balls[i];
  balls.splice(i, 1);
  ball.ball.src = ponifyJenkinsRootUrl + "/plugin/ponify/svg/" + ball.color + ".svg";
  if (balls.length > 0) {
    setTimeout(function() { replaceBall(balls); }, Math.floor((Math.random() * 1200) + 300)); 
  }
}

function findBalls() {
  var balls = [];
  var images = document.getElementsByTagName("img");
  for (var i = 0; i < images.length; i++) {
    var match = /\/(\d{2}x\d{2})\/(grey|disabled|aborted|nobuilt|blue|red|yellow)(_anime|)\.(gif|png)/.exec(images[i].src);
    if (match !== null) {
      var color = "grey";
      if (match[2] === "blue") {
        color = "green";
      } else if (match[2] === "red") {
        color = "red";
      } else if (match[2] === "yellow") {
        color = "yellow";
      }
      var ball = { ball: images[i], size: match[1], color: color, anime: match[3] };
      balls.push(ball);
    }
  }
  setTimeout(function() { replaceBall(balls); }, 400);
}

function doPonified(ponifiedText) {
  ponifiedText.style.opacity = "1";
  setTimeout(function() { findBalls(); }, 100);
}

function ponified(ponifiedText) {
  ponifiedText.src = ponifyJenkinsRootUrl + "/plugin/ponify/animation/ponified-text.png";
  setTimeout(function() { doPonified(ponifiedText); }, 100);
}

function animateRainbowTransition(dashFly, dashRainbow, jenkinsHead, rainbow, dashHead, title, ponifiedText, pos) {
  // rainbow from right to left
  var pixelPerSec = 200;
  var widthRainbow = pos + 89;
  var widthHead = 48 + 89;
  var durationRainbow = widthRainbow / pixelPerSec;
  var durationHead = widthHead / pixelPerSec;
  var startRainbow = 0.2;
  var startHead = durationRainbow - durationHead + startRainbow;
  var transitionRainbow = "left " + durationRainbow + "s linear " + startRainbow + "s";
  var transitionHead = "left " + durationHead + "s linear " + startHead + "s";
  dashFly.style.transition = transitionRainbow;
  dashRainbow.style.transition = transitionRainbow;
  rainbow.style.transition = transitionRainbow;
  jenkinsHead.style.transition = transitionHead;
  
  // dash back in from left
  var pixelPerSec2 = 50;
  var widthDash = 55 + 16
  var durationDash = widthDash / pixelPerSec2;
  var startDash = startRainbow + durationRainbow + 0.2;
  var transitionDash = "left " + durationDash + "s cubic-bezier(0.34,0.91,0.62,1.21) " + startDash + "s";
  var transitionTitle = "left " + durationDash + "s linear " + startDash + "s";
  dashHead.style.transition = transitionDash;
  title.style.transition = transitionTitle;
  
  // ponify
  var ponifiedStart = startDash + durationDash - 1;
  ponifiedText.style.transition = "opacity 1s ease-in-out 0.5s";
  
  // rainbow from right to left
  rainbow.style.left = "0";
  dashFly.style.left = "-89px";
  dashRainbow.style.left = "-89px";
  jenkinsHead.style.left = "-137px";
  
  // dash back in from left
  dashHead.style.left = "16px";
  title.style.left = "48px";
  
  setTimeout(function () { ponified(ponifiedText); }, ponifiedStart * 1000);
}

function ponify() {
  var header = document.getElementById("header");
  if (!header) {
    return;
  }
  
  var jenkinsHead = document.getElementById("jenkins-head-icon");
  if (!jenkinsHead) {
    return;
  }
  
  var jenkinsText = document.getElementById("jenkins-name-icon");
  if (!jenkinsText) {
    return;
  }
  
  var width = header.clientWidth;
  var dashFly = document.createElement("img");
  dashFly.src = ponifyJenkinsRootUrl + "/plugin/ponify/animation/dash-fly.png";
  dashFly.style.position = "absolute";
  dashFly.style.left = (width + 100) + "px";
  dashFly.style.top = "0";
  dashFly.style.bottom = "0";
  dashFly.style.zIndex = "1000";
  var dashRainbow = document.createElement("img");
  dashRainbow.src = ponifyJenkinsRootUrl + "/plugin/ponify/animation/dash-rainbow.png";
  dashRainbow.style.position = "absolute";
  dashRainbow.style.left = (width + 100) + "px";
  dashRainbow.style.top = "0";
  dashRainbow.style.bottom = "0";

  jenkinsHead.style.position = "relative";
  jenkinsHead.style.top = "0";
  
  var rainbow = document.createElement("img");
  rainbow.src = ponifyJenkinsRootUrl + "/plugin/ponify/animation/rainbow.png";
  rainbow.style.position = "absolute";
  rainbow.style.left = (width + 189) + "px";
  rainbow.style.height = "40px";
  rainbow.style.width = "100%";
  var rainbowDiv = document.createElement("div");
  rainbowDiv.style.display = "inline";
  rainbowDiv.style.position = "absolute";
  rainbowDiv.style.width = "100%";
  rainbowDiv.style.overflow = "hidden";
  rainbowDiv.style.margin = "0";
  rainbowDiv.style.padding = "0";
  rainbowDiv.style.border = "0";
  rainbowDiv.appendChild(dashFly);
  rainbowDiv.appendChild(dashRainbow);
  rainbowDiv.appendChild(rainbow);
  
  var dashHead = document.createElement("img");
  dashHead.src = ponifyJenkinsRootUrl + "/plugin/ponify/dash-head.png";
  dashHead.style.position = "absolute";
  dashHead.style.left = "-71px";
  dashHead.style.top = "0";
  
  jenkinsText.src = ponifyJenkinsRootUrl + "/plugin/ponify/animation/jenkins-text.png";
  jenkinsText.style.position = "relative";
  jenkinsText.style.top = "0";
  jenkinsText.style.height = "40px";
  
  var ponifiedText = document.createElement("img");
  ponifiedText.style.left = "-116px";
  ponifiedText.style.width = "164px";
  ponifiedText.style.height = "40px";
  ponifiedText.style.position = "relative";
  ponifiedText.style.opacity = "0";
  
  header.insertBefore(rainbowDiv, header.firstChild);
  jenkinsHead.parentNode.insertBefore(dashHead, jenkinsHead);
  jenkinsText.parentNode.appendChild(ponifiedText);

  jenkinsHead.style.left = "0";
  jenkinsText.style.left = "0";
  setTimeout(function() { animateRainbowTransition(dashFly, dashRainbow, jenkinsHead, rainbow, dashHead, jenkinsText, ponifiedText, width); }, 3000);
}