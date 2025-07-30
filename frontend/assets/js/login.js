document.getElementById("formAuthentication").addEventListener("submit", async function (e) {
  e.preventDefault();

  const email = document.getElementById("email").value.trim();
  const password = document.getElementById("password").value;

  function showBootstrapModal(message, title = "Notification") {
    const modalLabel = document.getElementById("responseModalLabel");
    const modalMessage = document.getElementById("modalMessage");

    modalLabel.textContent = title;
    modalMessage.innerHTML = message;

    const modal = new bootstrap.Modal(document.getElementById("responseModal"));
    modal.show();
  }

   async function getToken() {
    const response = await fetch("http://localhost:8081/api/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        email: "george@mailinator.com",
        password: "password"
      })
    });

  if (!response.ok) {
    const error = await response.text();
    throw new Error(`Failed to get token: ${response.status} - ${error}`);
}

const data = await response.json();
localStorage.setItem("jwtToken", data.token);
return data.token;

}

  try {
    const response = await fetch("http://localhost:8081/api/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ email, password })
    });

    const result = await response.json();

    if (!response.ok) {
      showBootstrapModal(`❌ ${result.message || "Login failed."}`, "Error");
      return;
    }

    localStorage.setItem("jwtToken", result.token);
    showBootstrapModal(`✅ Login successful for ${email}`, "Success");

    // Optional: redirect after 2 seconds
    setTimeout(() => {
     window.location.assign("index.html");
    }, 2000);

  } catch (error) {
    console.error("Login error:", error);
    showBootstrapModal("⚠️ Something went wrong. Please try again.", "Server Error");
  }
});