import React, { Component } from "react";
class ComponentsTopChild extends Component {
  render() {
    return <h3>Dữ liệu nhận được từ App: {this.props.dataToTopChild} </h3>;
  }
}

export default ComponentsTopChild;