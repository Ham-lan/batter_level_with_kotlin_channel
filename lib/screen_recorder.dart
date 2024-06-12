import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class ScreenRecorder {
  static const MethodChannel _channel = MethodChannel('flutter_screen_record');

  static Future<void> startScreenRecord() async {
    try {
      final result = await _channel.invokeMethod('startScreenRecord');
      print(result);
    } on PlatformException catch (e) {
      print("Failed to start screen recording: ${e.message}");
    }
  }
}
