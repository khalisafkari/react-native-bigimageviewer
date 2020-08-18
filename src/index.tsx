import { NativeModules } from 'react-native';

type BigimageviewerType = {
  multiply(a: number, b: number): Promise<number>;
};

const { Bigimageviewer } = NativeModules;

export default Bigimageviewer as BigimageviewerType;
