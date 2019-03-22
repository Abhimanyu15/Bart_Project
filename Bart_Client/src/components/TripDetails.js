
import React, { Component } from 'react'
import { Grid, GridColumn as Column } from '@progress/kendo-react-grid'
import { process } from '@progress/kendo-data-query'
import './Station.css'
import '@progress/kendo-theme-bootstrap'
export default class TripDetails extends Component {
  
    constructor(props) {
      super(props)
      this.claimState = {}
      this.state = {
        data: this.props.data,
      }
      
      
    };
    render(){
        console.log("this.state.data ,",this.props.data)
        return(
          <div className='define-bounty-component'>
            
            <h3>My Trip Details</h3> 

           {
                            
                           
                                
            <Grid
                style={{ height: '800px' }}
                resizable={true}
                reorderable={true}
                filterable={true}
                sortable={true}
                pageable={{ pageSizes: true }}
                groupable={true}
    
                data={this.props.data}
                onDataStateChange={this.dataStateChange}
                {...this.state.dataState}
    
                onExpandChange={this.expandChange}
                expandField="expanded"
            >
                
                <Column field="@origin" title="Origin Station" width="200px" />
                <Column field="@destination" title="Destination station" width="200px" />
                <Column field="@origTimeMin" title="Origin time" width="200px" />
                <Column field="@origTimeDate" title="Origin Date " width="200px"  />
                <Column field="@fare" title="Fare" width="200px" />
                <Column field="@tripTime" title="Trip time" width="200px" />
                <Column field="@destTimeMin" title="Destination Time" width="200px" />
                <Column field="@destTimeDate" title="Destination Date" width="200px" />
                
                {/* <Column field="avg_percentage" title="% Row Passed" width="200px" filter="numeric" cell={uatReportingCell} /> */}
                {/* <Column field="avg_percentage" title="Avg Delta Percentage" width="200px" filter="numeric" cell={uatReportingCell}  /> */}
                
                {/* <Column field="ihp_file_link" title="IHP Link" width="200px"  cell={uatIHPCell} /> */}
                {/* <Column field="ihp_file_link" title="IHP Link" width="200px"  cell={ (props) => 
                        <td>
                        
                            <a href={props.dataItem.ihp_file_link} target="_blank" style={{color: "#106ba3"}}>
                            {props.dataItem.ihp_file_link}
                            </a>
                        </td>} 
                /> */}
                {/* <Column field="aws_file_link" title="AWS Link" width="200px"  cell={uatAWSCell} /> */}
                {/* <Column field="aws_file_link" title="AWS Link" width="200px"  cell={ (props) => 
                        <td>
                            <a href={props.dataItem.aws_file_link} target="_blank" style={{color: "#106ba3"}}>
                            AWS Query Results(s3)
                            </a>
                        </td>} 
                /> */}
    
                {/* <Column field="s3_presign_url" title="S3 Link" width="200px"  cell={uatS3Cell} /> */}
    
                {/* <Column field="s3_presign_url" title="S3 Link" width="200px"  cell={ (props) => 
                        <td>
                            <a href={props.dataItem.s3_presign_url} target="_blank" style={{color: "#106ba3"}}>
                            UAT Diff Results(s3)
                            </a>
                        </td>} 
                /> */}
                {/* <Column field="result_set_url" title="Result S3 Url" width="200px" /> */}
                
    
            </Grid>
        }
        </div>
        )
    }
                
    createGroupState(dataState) {
        return {
          result: process(this.props.data, dataState),
          dataState: dataState
        }
      }
    
      dataStateChange(event) {
        this.setState({
          ...this.state,
          ...this.createGroupState(event.data)
        })
      }
    
      expandChange(event) {
        event.dataItem[event.target.props.expandField] = event.value
        this.setState({
          result: Object.assign({}, this.state.result),
          dataState: this.state.dataState
        });
      }
    
      componentDidUpdate(prevProps) {
        if (prevProps.data.length !== this.props.data.length) {
          this.setState({
            ...this.state,
            ...this.createGroupState({
              take: 100,
              group: Array(0),
              sort: undefined,
              filter: undefined,
              skip: 0
            })
          })
        }
      }
    
      componentDidMount(prevProps) {
        this.setState({
          ...this.state,
          ...this.createGroupState({
            take: 100,
            group: Array(0),
            sort: undefined,
            filter: undefined,
            skip: 0
          })
        })
      }
    
};