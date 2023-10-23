import React from "react";

export default class TopTenLines extends React.Component {
  constructor(props) {
    super(props);

    this.getStopNames = this.getStopNames.bind(this);
    const { lineNumber } = this.props;
    this.state = {
      url: `http://localhost:8080/api/stopsbyname/${lineNumber}`,
      loading: false,
      items: [],
      fetched: false,
      lineNumber,
    };
  }
  async componentDidMount() {}
  static getDerivedStateFromProps(nextProps, state) {
    return state;
  }

  async getStopNames() {
    const response = await fetch(this.state.url);
    const items = await response.json();
    if (this.state.fetched) {
      this.setState({
        items: [],
        fetched: false,
      });
    } else {
      this.setState({
        items,
        fetched: true,
      });
    }
  }

  render() {
    return (
      <React.Fragment>
        <button type="button" class="btn btn-info" onClick={this.getStopNames}>{this.state.lineNumber}</button>
        <StopNamesInCurrentLine items={this.state.items} />
      </React.Fragment>
    );
  }
}

const StopNamesInCurrentLine = ({ items }) => {
  if (items && items.length) {
    return items.map((item) => (
      <React.Fragment>
        {/* <div>{item.id}</div> */}
        <div><h5>{item.stopName}</h5></div>
        {/* <div>{item.stopPoint}</div> */}
        <br></br>
      </React.Fragment>
    ));
  }
  return null;
};
