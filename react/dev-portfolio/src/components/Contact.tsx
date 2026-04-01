import { motion } from 'motion/react';
import { Mail, Github, Linkedin, MessageSquare, Send } from 'lucide-react';
import { SOCIAL_LINKS } from '../constants';

export default function Contact() {
  return (
    <section id="contact" className="py-32 px-6 bg-white">
      <div className="max-w-7xl mx-auto">
        <div className="grid lg:grid-cols-2 gap-20">
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            viewport={{ once: true }}
          >
            <span className="text-[#f4a261] font-mono text-sm tracking-widest uppercase mb-4 block">Get in touch</span>
            <h2 className="text-5xl md:text-7xl font-bold text-[#2d3436] tracking-tighter mb-8">LET'S WORK <br />TOGETHER</h2>
            <p className="text-[#636e72] text-lg mb-12 max-w-md">
              Have a project in mind or just want to say hi? 
              Feel free to reach out through the form or any of my social channels.
            </p>

            <div className="space-y-6">
              <a href={`mailto:${SOCIAL_LINKS.email}`} className="flex items-center space-x-4 text-[#2d3436] hover:text-[#8da9c4] transition-colors group">
                <div className="p-4 bg-[#fdfbf7] rounded-2xl group-hover:bg-[#8da9c4]/10 transition-colors">
                  <Mail size={24} />
                </div>
                <div>
                  <p className="text-xs text-[#636e72] uppercase tracking-widest font-mono">Email Me</p>
                  <p className="text-lg font-medium">{SOCIAL_LINKS.email}</p>
                </div>
              </a>
              
              <div className="flex space-x-4 pt-6">
                <a href={SOCIAL_LINKS.github} target="_blank" rel="noopener noreferrer" className="p-4 bg-[#fdfbf7] rounded-2xl text-[#2d3436] hover:bg-[#8da9c4] hover:text-white transition-all">
                  <Github size={24} />
                </a>
                <a href={SOCIAL_LINKS.linkedin} target="_blank" rel="noopener noreferrer" className="p-4 bg-[#fdfbf7] rounded-2xl text-[#2d3436] hover:bg-[#8da9c4] hover:text-white transition-all">
                  <Linkedin size={24} />
                </a>
                <a href={SOCIAL_LINKS.blog} target="_blank" rel="noopener noreferrer" className="p-4 bg-[#fdfbf7] rounded-2xl text-[#2d3436] hover:bg-[#8da9c4] hover:text-white transition-all">
                  <MessageSquare size={24} />
                </a>
              </div>
            </div>
          </motion.div>

          <motion.div
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            viewport={{ once: true }}
            className="bg-[#fdfbf7] border border-[#8da9c4]/10 p-8 md:p-12 rounded-[40px] shadow-sm"
          >
            <form className="space-y-6" onSubmit={(e) => e.preventDefault()}>
              <div className="grid md:grid-cols-2 gap-6">
                <div className="space-y-2">
                  <label className="text-xs font-mono uppercase tracking-widest text-[#636e72] ml-1">Name</label>
                  <input 
                    type="text" 
                    placeholder="John Doe"
                    className="w-full bg-white border border-[#8da9c4]/10 rounded-2xl px-6 py-4 text-[#2d3436] placeholder:text-[#636e72]/40 focus:outline-none focus:border-[#8da9c4] transition-colors"
                  />
                </div>
                <div className="space-y-2">
                  <label className="text-xs font-mono uppercase tracking-widest text-[#636e72] ml-1">Email</label>
                  <input 
                    type="email" 
                    placeholder="john@example.com"
                    className="w-full bg-white border border-[#8da9c4]/10 rounded-2xl px-6 py-4 text-[#2d3436] placeholder:text-[#636e72]/40 focus:outline-none focus:border-[#8da9c4] transition-colors"
                  />
                </div>
              </div>
              <div className="space-y-2">
                <label className="text-xs font-mono uppercase tracking-widest text-[#636e72] ml-1">Message</label>
                <textarea 
                  rows={5}
                  placeholder="Tell me about your project..."
                  className="w-full bg-white border border-[#8da9c4]/10 rounded-2xl px-6 py-4 text-[#2d3436] placeholder:text-[#636e72]/40 focus:outline-none focus:border-[#8da9c4] transition-colors resize-none"
                />
              </div>
              <button 
                type="submit"
                className="w-full bg-[#8da9c4] hover:bg-[#7a9e9f] text-white font-bold py-5 rounded-2xl flex items-center justify-center space-x-2 transition-all active:scale-[0.98] shadow-lg shadow-[#8da9c4]/20"
              >
                <span>SEND MESSAGE</span>
                <Send size={18} />
              </button>
            </form>
          </motion.div>
        </div>
      </div>
    </section>
  );
}
