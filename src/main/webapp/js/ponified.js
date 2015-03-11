function ponified() {
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

  header.style.backgroundImage = "url(/jenkins/plugin/ponify/animation/rainbow.png)";
  jenkinsHead.src = ponifyJenkinsRootUrl + "/plugin/ponify/dash-head.png";
  jenkinsHead.style.top = "0";
  jenkinsText.src = ponifyJenkinsRootUrl + "/plugin/ponify/jenkins-text-ponified.png";
  jenkinsText.style.top = "0";
  jenkinsText.style.height = "40px";
  jenkinsText.style.width = "164px";
}
