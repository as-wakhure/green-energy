<%@ page contentType="text/html charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Create Account</title>
<style>
:root{
--bg:#f6f8fb;--card:#fff;--accent:#4f46e5;--muted:#6b7280;--danger:#ef4444;
--radius:12px; --glass: rgba(255,255,255,0.6);
font-family: Inter, ui-sans-serif, system-ui, -apple-system, "Segoe UI", Roboto, "Helvetica Neue", Arial;
}
*{box-sizing:border-box}
body{margin:0;background:linear-gradient(180deg,#eef2ff 0%,var(--bg) 100%);color:#111}


/* Layout */
.container{min-height:100vh;display:flex;align-items:center;justify-content:center;padding:32px}
.card{width:100%;max-width:980px;background:var(--card);border-radius:16px;box-shadow:0 10px 30px rgba(2,6,23,0.08);display:grid;grid-template-columns:360px 1fr;overflow:hidden}


/* Left - sidebar */
.sidebar{background:linear-gradient(180deg,var(--accent),#7c3aed);color:#fff;padding:28px;display:flex;flex-direction:column;gap:18px}
.brand{display:flex;align-items:center;gap:12px}
.logo{width:48px;height:48px;border-radius:10px;background:rgba(255,255,255,0.12);display:grid;place-items:center;font-weight:700}
.sidebar h2{margin:0;font-size:20px}
.sidebar p{margin:0;opacity:0.95}
.features{margin-top:6px;display:flex;flex-direction:column;gap:10px}
.feature{display:flex;gap:12px;align-items:center}
.feature .dot{width:10px;height:10px;border-radius:50%;background:rgba(255,255,255,0.3)}


/* Right - form area */
.form-area{padding:32px}
.topbar{display:flex;justify-content:flex-end;gap:12px;align-items:center;margin-bottom:8px}
.topbar .login{font-size:14px;color:var(--muted)}


h1{margin:6px 0 18px;font-size:22px}
form{display:grid;gap:12px}
.row{display:flex;gap:12px}
.col{flex:1}
label{display:block;font-size:13px;margin-bottom:6px;color:var(--muted)}
input[type=text], input[type=email], input[type=password], input[type=tel]{width:100%;padding:12px;border-radius:10px;border:1px solid #e6e9ef;background:#fbfdff;font-size:14px}
input:focus{outline:none;border-color:var(--accent);box-shadow:0 6px 18px rgba(79,70,229,0.08)}
.help{font-size:12px;color:var(--muted)}


.pw-row{display:flex;gap:8px;align-items:center}
.pw-toggle{background:transparent;border:none;cursor:pointer;padding:6px;border-radius:8px}
.strength{height:8px;border-radius:8px;background:#eef2ff;overflow:hidden}
.strength > i{display:block;height:100%;width:0%;background:linear-gradient(90deg,#f97316,#84cc16);transition:width .2s}


.error{color:var(--danger);font-size:13px}
.actions{display:flex;justify-content:space-between;align-items:center;margin-top:8px}
.btn{background:var(--accent);color:#fff;padding:10px 16px;border-radius:10px;border:none;cursor:pointer;font-weight:600}
.btn.secondary{background:transparent;color:var(--accent);border:1px solid #e7e6ff}


.terms{display:flex;align-items:center;gap:8px;font-size:13px;color:var(--muted)}


/* Responsive */
@media (max-width:880px){
.card{grid-template-columns:1fr;padding:0}
.sidebar{display:none}
.container{padding:18px}
}
</style>
</head>
<body>
<div class="container">
<div class="card" role="region" aria-label="Create account">


<aside class="sidebar" aria-hidden="false">
<div class="brand">
<div class="logo">A</div>
<div>
<h2>Ultra Green Energy</h2>
<div style="font-size:13px;opacity:0.95">Eco-friendly.</div>
</div>
</div>
<p>Create your account</p>
</div>
</div>


<script>
// Simple client-side validation + password-strength
const form = document.getElementById('signup');
const email = document.getElementById('email');
const first = document.getElementById('first');
const username = document.getElementById('username');
const password = document.getElementById('password');
const confirm = document.getElementById('confirm');
const terms = document.getElementById('terms');


const err = (id, msg) => document.getElementById(id).textContent = msg || '';


function scorePassword(pw){
let score = 0;
if(!pw) return 0;
if(pw.length >= 8) score += 25;
if(/[a-z]/.test(pw) && /[A-Z]/.test(pw)) score += 20;
if(/\d/.test(pw)) score += 20;
if(/[^A-Za-z0-9]/.test(pw)) score += 20;
if(pw.length >= 12) score += 15;
return Math.min(100, score);
}


const strengthBar = document.getElementById('strengthBar');
password.addEventListener('input', ()=>{
const s = scorePassword(password.value);
strengthBar.style.width = s + '%';
if(s<40) strengthBar.parentElement.style.background = '#fee2e2';
else strengthBar.parentElement.style.background = '#eef2ff';
});


// toggle show/hide password
document.getElementById('togglePw').addEventListener('click', ()=>{
const t = password.type === 'password' ? 'text' : 'password';
password.type = t;
confirm.type = t;
document.getElementById('togglePw').textContent = t === 'text' ? 'Hide' : 'Show';
});


// basic email check
function validEmail(v){
return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(v);
}


form.addEventListener('submit', (e)=>{
e.preventDefault();
let ok = true;
// reset
err('err-first',''); err('err-email',''); err('err-username',''); err('err-password',''); err('err-confirm','');


if(!first.value.trim()){ err('err-first','First name is required'); ok=false; }
if(!validEmail(email.value)){ err('err-email','Please enter a valid email'); ok=false; }
if(!username.value || username.value.length < 3){ err('err-username','Username must be at least 3 characters'); ok=false; }
if(scorePassword(password.value) < 40){ err('err-password','Choose a stronger password (8+ chars, mix of types)'); ok=false; }
if(password.value !== confirm.value){ err('err-confirm','Passwords do not match'); ok=false; }
if(!terms.checked){ alert('You must accept the terms to continue'); ok=false; }


if(!ok) return;


// At this point, the form is ready to submit to server.
// Replace this with actual fetch/POST to your backend.
const payload = {
first: first.value.trim(),
last: document.getElementById('last').value.trim(),
email: email.value.trim(),
username: username.value.trim(),
phone: document.getElementById('phone').value.trim()
};


// Demo: show a friendly message (replace with real submission code)
alert('Account ready to be submitted — replace this alert with real network code.');
console.log('payload',payload);
// Example: fetch('/api/signup',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify(payload)})
});


// Demo button
document.getElementById('demoBtn').addEventListener('click', ()=>{
first.value = 'Demo'; document.getElementById('last').value = 'User'; email.value = 'demo@example.com'; username.value='demouser'; password.value='Demo123!@#'; confirm.value='Demo123!@#'; terms.checked = true;
password.dispatchEvent(new Event('input'));
});
</script>
</body>
</html>