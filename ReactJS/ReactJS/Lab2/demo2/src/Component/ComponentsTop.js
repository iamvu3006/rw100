// rcc
import React, { Component } from 'react';

class ComponentsTop extends Component {
    constructor(props) {
        super(props);
        // Khai báo state để lưu trữ dữ liệu
        this.state = {
            click_number: 0
        }
    }
    // Hàm xử lý khi nhấn nút sent Data
    handleClick() {
        this.setState({ click_number: this.state.click_number + 1 });
        // Tự động được render lại khi state thay đổi
        console.log("Number of times clicked = ", this.state.click_number);
    }
    // Hàm render: hiển thị giao diện của component
    render() {
        // Nhận dữ liệu từ App truyền xuống
        // this.props
        // let data1 = this.props.data1
        // let data2 = this.props.data2
        // Destructuring
        let { data1, data2, data3, heading } = this.props;

        console.log("ComponentsTop: v_data1 = ", data1);
        console.log("ComponentsTop: v_data2 = ", data2);
        console.log("ComponentsTop: v_data3 = ", data3);
        console.log("ComponentsTop: heading = ", heading);
        return (
            <div class="row">
                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            {heading}
                        </div>
                        <div class="panel-body">

                            <div class="row">
                                <div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
                                    <input type="text" class="form-control" id="" placeholder="Input field" value={this.state.click_number} />
                                </div>

                                <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                                    <button type="button" class="btn btn-danger" onClick={() => this.handleClick()}>Sent Data</button>
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