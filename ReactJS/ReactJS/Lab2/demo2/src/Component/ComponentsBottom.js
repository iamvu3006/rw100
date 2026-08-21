// rsf
import React from 'react';

function ComponentsBottom(props) {
    // let data1 = props.data1;
    let { data1, heading_bottom } = props;
    console.log("ComponentsBottom: v_data1 = ", data1);
    // 
    return (
        <div className="row" >
            <div className="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <div className="panel panel-warning">
                    <div className="panel-heading">{heading_bottom}</div>
                    <div className="panel-body">
                        <div>
                            <textarea name="" id="input" className="form-control" rows="3" required="required">Hello VTI</textarea>
                        </div>
                    </div>
                </div>
            </div>
        </div >

    );
}

export default ComponentsBottom;