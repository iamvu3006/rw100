// rsf
import React from 'react';

function ComponentsBottom(props) {
    let { data, message, heading_bottom } = props;
    console.log("ComponentsBottom: nhận data =", data);

    return (
        <div className="row">
            <div className="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <div className="panel panel-warning">
                    <div className="panel-heading">{heading_bottom || "-- COMPONENT BOTTOM ---"}</div>
                    <div className="panel-body">
                        {message && <h4>{message}</h4>}
                        <div>
                            <textarea 
                                name="" 
                                id="input" 
                                className="form-control" 
                                rows="3" 
                                value={data || ""} 
                                placeholder="Dữ liệu từ Component Top sẽ hiển thị ở đây..."
                                readOnly 
                            />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ComponentsBottom;