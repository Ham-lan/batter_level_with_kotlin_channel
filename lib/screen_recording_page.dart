import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
// import 'package:flutter_screen_record/flutter_screen_record.dart';

void main() {
  runApp(ScreenRecording());
}

class ScreenRecording extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Screen Recording Example'),
        ),
        body: Center(
          child: ElevatedButton(
            onPressed: () async {
              await FlutterScreenRecord.startScreenRecord();
            },
            child: Text('Start Recording'),
          ),
        ),
      ),
    );
  }
}

class FlutterScreenRecord {
  static const MethodChannel _channel = MethodChannel('flutter_screen_record');

  static Future<void> startScreenRecord() async {
    final Map<String, dynamic> params = <String, dynamic>{
      'resultCode': 200,
      // 'data': null, // Pass the data intent received from the onActivityResult in Flutter
    };

    await _channel.invokeMethod('startScreenRecord', params);
  }
}
