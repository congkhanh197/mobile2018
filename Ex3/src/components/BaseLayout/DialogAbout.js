/* @flow */

import * as React from 'react';
import { Text, TouchableOpacity, Linking } from 'react-native';
import {
  Paragraph,
  Button,
  Dialog,
  DialogTitle,
  DialogActions,
  DialogContent,
} from 'react-native-paper';
import Icon from 'react-native-vector-icons/Ionicons';

const DialogAbout = ({
  visible,
  close,
}: {
  visible: boolean,
  close: Function,
}) => (
  <Dialog
    onDismiss={close}
    style={{ backgroundColor: '#2B7FC3' }}
    visible={visible}
  >
    <DialogTitle style={{ color: 'white' }}>About</DialogTitle>
    <DialogActions>
      <Button color="white" onPress={close}>
        CLOSE
      </Button>
    </DialogActions>
  </Dialog>
);

export default DialogAbout;
