<%@ page contentType="text/html charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Homepage</title>
  <!-- Bootstrap 5 CSS -->
 <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">-->
  <style>
    body {
      min-height: 100vh;
      display: flex;
      flex-direction: column;
    }
    .wrapper {
      display: flex;
      flex: 1;
    }
    .sidebar {
      width: 220px;
      min-height: 100vh;
      background-color: #343a40;
      color: #fff;
      padding-top: 1rem;
    }
    .sidebar a {
      color: #adb5bd;
      text-decoration: none;
      display: block;
      padding: 0.75rem 1rem;
    }
    .sidebar a:hover {
      background-color: #495057;
      color: #fff;
    }
    .content {
      flex: 1;
      padding: 1rem;
      background-color: #f8f9fa;
    }
    .topbar {
      background-color: #fff;
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
      padding: 0.5rem 1rem;
    }
  </style>
</head>
<body>

  <!-- Top Navbar -->
  <nav class="navbar topbar">
    <div class="container-fluid justify-content-between">
      <span class="navbar-brand mb-0 h5">Ultra Green Energy</span>
      <div class="d-flex align-items-center">
        <span class="me-3">Welcome</span>
        <button class="btn btn-outline-danger btn-sm">Logout</button>
      </div>
    </div>
  </nav>

  <!-- Page Wrapper -->
  <div class="wrapper">
    <!-- Sidebar -->
    <div class="sidebar">
      <h6 class="text-uppercase px-3 mb-3">Menu</h6>
      <a href="#"> Dashboard</a>
      <a href="#"> Energy Audit</a>
    </div>

    <!-- Main Content -->
    <div class="content">
      <h2>Dashboard</h2>
      <p>This is your main content area.</p>
    </div>
  </div>

  <!-- Bootstrap JS -->
  <!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>-->
</body>
</html>