import React from "react";
import { BrowserRouter as Router, Route } from "react-router-dom";
import { Provider } from "react-redux";
import store from "./store";
import "./App.css";
import Header from "./components/Layout/Header";
import UpdateProject from "./components/Project/UpdateProject";
import AddProject from "./components/Project/AddProject";
import Dashboard from "./components/dashboard";
import ProjectBoard from "./components/Project/ProjectBoard/Projectboard";
import AddProjectTask from "./components/Project/ProjectBoard/ProjecTasks/AddProjectTask";

function App() {
  return (
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header />
          <Route exact path="/Dashboard" component={Dashboard} />
          <Route exact path="/addProject" component={AddProject} />
          <Route exact path="/updateProject/:id" component={UpdateProject} />
          <Route exact path="/projectBoard/:id" component={ProjectBoard} />
          <Route exact path="/addProjectTask/:id" component={AddProjectTask} />
        </div>
      </Router>
    </Provider>
  );
}

export default App;
