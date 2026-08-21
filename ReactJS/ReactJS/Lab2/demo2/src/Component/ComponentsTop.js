// rcc
import React, { Component } from 'react';
import ComponentsTopChild from './ComponentsTopChild';

class ComponentsTop extends Component {
    constructor(props) {
        super(props);
        // Khai báo state để lưu trữ dữ liệu từ input field
        this.state = {
            inputData: ''
        };
    }

    // Lắng nghe sự thay đổi của khung input
    handleChange = (e) => {
        this.setState({ inputData: e.target.value });
    };

    // Hàm xử lý khi nhấn nút Sent Data
    handleClick = () => {
        let dataTop = this.state.inputData || "VTI Academy";
        // Gọi callback function được truyền từ App qua props
        if (this.props.getData) {
            this.props.getData(dataTop);
        } else if (this.props.getDataFromTop) {
            this.props.getDataFromTop(dataTop);
        }
    };

    // Hàm render: hiển thị giao diện của component
    render() {
        let { heading_panel, heading, dataToTopChild } = this.props;

        return (
            <div className="row">
                <div className="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <div className="panel panel-primary">
                        <div className="panel-heading">
                            {heading_panel || heading || "------ Component Top -------"}
                        </div>
                        <div className="panel-body">
                            {dataToTopChild && <ComponentsTopChild dataToTopChild={dataToTopChild} />}
                            <div className="row">
                                <div className="col-xs-10 col-sm-10 col-md-10 col-lg-10">
                                    <input 
                                        type="text" 
                                        className="form-control" 
                                        placeholder="Nhập dữ liệu gửi xuống Bottom..."
                                        value={this.state.inputData}
                                        onChange={this.handleChange}
                                    />
                                </div>

                                <div className="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                                    <button type="button" className="btn btn-danger" onClick={this.handleClick}>Sent Data</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default ComponentsTop;